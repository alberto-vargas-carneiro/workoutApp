'use client'

import style from './page.module.css';
import { FaArrowLeft } from "react-icons/fa";
import { useParams } from 'next/navigation';
import { useEffect, useState } from 'react';
import * as workoutService from '../../services/workout-service';
import WorkoutItemsCard from '@/app/cards/workout-cards/items/page';
import SetsCard from '@/app/cards/workout-cards/sets/page';

interface WorkoutSet {
  id: number;
  exerciseName: string;
  setNumber: number;
  reps: string;
  rest: number;
  weight: number | null;
}

interface GroupedWorkout {
  exerciseName: string;
  sets: WorkoutSet[];
}

export default function WorkoutDetailsPage() {

  const { id } = useParams();
  const [loading, setLoading] = useState(true);
  const [groupedItems, setGroupedItems] = useState<GroupedWorkout[]>([]);

  useEffect(() => {
    if (id) {
      workoutService.getWorkoutById(Number(id))
        .then(response => {
          setLoading(false);
          const grouped = response.data.workoutItems.reduce((acc: Record<string, WorkoutSet[]>, item: WorkoutSet) => {
            if (!acc[item.exerciseName]) {
              acc[item.exerciseName] = [];
            }
            acc[item.exerciseName].push(item);
            return acc;
          }, {});
          console.log(grouped);
          const groupedArray = Object.keys(grouped).map(exerciseName => ({
            exerciseName,
            sets: grouped[exerciseName]
          }));

          setGroupedItems(groupedArray);

        })
        .catch(error => {
          console.error(error);
          setLoading(false);
        });
    }
  }, [id]);

  if (loading) return <div>Carregando...</div>;

  return (
    <div>
      <div className={style.container}>
        <div className={style.title}>
          <FaArrowLeft className={style.arrow} />
          <h1>Peito</h1>
        </div>
        <div className={style.items_container}>
          {groupedItems.map(group => (
            <>
              <WorkoutItemsCard key={group.exerciseName} name={group.exerciseName} />
              {group.sets.map(set => (
                <SetsCard key={set.id} set={set.setNumber} weight={set.weight} reps={set.reps} rest={set.rest} />
              ))}
            </>
          ))}
        </div>
      </div>
    </div>
  );
}
