'use client'

import style from './page.module.css';
import { FaArrowLeft } from "react-icons/fa";
import { useParams } from 'next/navigation';
import { useEffect, useState } from 'react';
import * as workoutService from '@/app/services/workout-service';
import WorkoutItemsCard from '@/app/cards/workout-cards/items/page';
import SetsCard from '@/app/cards/workout-cards/sets/page';
import Link from 'next/link';

interface WorkoutItemDTO {
  id?: number;
  exerciseId: number;
  exerciseName: string;
  setNumber: number;
  reps: string;
  rest: number;
  weight: number;
  video?: string;
}

interface GroupedWorkout {
  exerciseName: string;
  exerciseId: number;
  sets: WorkoutItemDTO[];
}

interface WorkoutFullDTO {
  id: number;
  name: string;
  workoutItems: WorkoutItemDTO[];
}

export default function WorkoutDetailsPage() {
  const { id } = useParams();
  const [loading, setLoading] = useState(true);
  const [groupedItems, setGroupedItems] = useState<GroupedWorkout[]>([]);
  const [isEditing, setIsEditing] = useState(false);
  const [workoutName, setWorkoutName] = useState('');
  const [newSetCounter, setNewSetCounter] = useState<number>(-1);

  useEffect(() => {
    if (id) {
      workoutService.getWorkoutById(Number(id))
        .then(response => {
          const data: WorkoutFullDTO = response.data;
          setWorkoutName(data.name);

          const grouped = data.workoutItems.reduce((acc: Record<string, GroupedWorkout>, item: WorkoutItemDTO) => {
            if (!acc[item.exerciseName]) {
              acc[item.exerciseName] = {
                exerciseName: item.exerciseName,
                exerciseId: item.exerciseId,
                sets: []
              };
            }
            acc[item.exerciseName].sets.push(item);
            return acc;
          }, {} as Record<string, GroupedWorkout>);

          const groupedArray = Object.keys(grouped).map(key => grouped[key]);
          setGroupedItems(groupedArray);
          setLoading(false);
        })
        .catch(error => {
          console.error(error);
          setLoading(false);
        });
    }
  }, [id]);

  function handleSetChange(groupIndex: number, setIndex: number, field: keyof WorkoutItemDTO, value: any) {
    setGroupedItems(prev => prev.map((group, idx) => {
      if (idx !== groupIndex) return group;
      return { 
        ...group, 
        sets: group.sets.map((set, sIdx) => {
          if (sIdx !== setIndex) return set;
          return { ...set, [field]: value };
        })
      };
    }));
  }

  function handleAddSet(groupIndex: number) {
    setGroupedItems(prev => prev.map((group, idx) => {
      if (idx !== groupIndex) return group;
      const newId = newSetCounter;
      const newSet: WorkoutItemDTO = {
        id: newId,
        exerciseId: group.exerciseId,
        exerciseName: group.exerciseName,
        setNumber: group.sets.length + 1,
        reps: '',
        rest: 0,
        weight: 0
      };
      return { ...group, sets: [...group.sets, newSet] };
    }));
    setNewSetCounter(prev => prev - 1);
  }

  function handleRemoveSet(groupIndex: number, setIndex: number) {
    setGroupedItems(prev => prev.map((group, idx) => {
      if (idx !== groupIndex) return group;
      return { ...group, sets: group.sets.filter((_, i) => i !== setIndex) };
    }));
  }
  

  function buildPayload(): WorkoutFullDTO {
    const workoutItems: WorkoutItemDTO[] = [];
    groupedItems.forEach(group => {
      group.sets.forEach(set => {
        workoutItems.push(set);
      });
    });
    return {
      id: Number(id),
      name: workoutName,
      workoutItems
    };
  }

  async function handleSave() {
    try {
      const payload = buildPayload();
      await workoutService.updateWorkout(Number(id), payload);
      alert('Treino atualizado com sucesso!');
      setIsEditing(false);
    } catch (error) {
      console.error(error);
      alert('Erro ao atualizar o treino.');
    }
  }

  if (loading) return <div>Carregando...</div>;

  return (
    <div className={style.container}>
      <div className={style.title}>
        <Link href={'/workouts'}>
          <FaArrowLeft className={style.arrow} />
        </Link>
        {isEditing ? (
          <input
            type="text"
            value={workoutName}
            onChange={e => setWorkoutName(e.target.value)}
            className={style.edit_name_input}
          />
        ) : (
          <h1>{workoutName}</h1>
        )}
        <button onClick={() => setIsEditing(!isEditing)}>
          {isEditing ? "Cancelar edição" : "Editar"}
        </button>
      </div>

      <div className={style.items_container}>
        {groupedItems.map((group, gIndex) => (
          <div key={group.exerciseName} className={style.exercise_group}>
            <WorkoutItemsCard name={group.exerciseName} />
            {group.sets.map((set, sIndex) => (
              <div key={set.id} className={style.setRow}>
                {isEditing ? (
                  <>
                    <input
                      type="number"
                      value={set.setNumber}
                      onChange={e => handleSetChange(gIndex, sIndex, 'setNumber', parseInt(e.target.value))}
                      placeholder="Série"
                    />
                    <input
                      type="number"
                      value={set.weight ?? ''}
                      onChange={e => handleSetChange(gIndex, sIndex, 'weight', parseInt(e.target.value))}
                      placeholder="Peso"
                    />
                    <input
                      type="text"
                      value={set.reps}
                      onChange={e => handleSetChange(gIndex, sIndex, 'reps', e.target.value)}
                      placeholder="Reps"
                    />
                    <input
                      type="number"
                      value={set.rest}
                      onChange={e => handleSetChange(gIndex, sIndex, 'rest', parseInt(e.target.value))}
                      placeholder="Descanso"
                    />
                    <button onClick={() => handleRemoveSet(gIndex, sIndex)}>Remover</button>
                  </>
                ) : (
                  <SetsCard set={set.setNumber} weight={set.weight} reps={set.reps} rest={set.rest} />
                )}
              </div>
            ))}
            {isEditing && <button onClick={() => handleAddSet(gIndex)}>+ Adicionar Série</button>}
          </div>
        ))}
      </div>

      {isEditing && <button onClick={handleSave} className={style.saveButton}>Salvar alterações</button>}
    </div>
  );
}