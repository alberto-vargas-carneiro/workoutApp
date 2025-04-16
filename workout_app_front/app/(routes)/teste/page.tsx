'use client'

import { useEffect, useState } from "react";
import * as exerciseService from "@/app/services/exercise-service";
import * as workoutService from "@/app/services/workout-service";

interface Exercise {
    id: number;
    name: string;
}

interface WorkoutItem {
    exerciseId: number;
    setNumber: number;
    reps: string;
    rest: number;
    weight: number;
}

interface Workout {
    name: string;
    workoutItems: WorkoutItem[];
}

export default function WorkoutForm() {
    const [exercises, setExercises] = useState<Exercise[]>([]);
    const [selectedExercises, setSelectedExercises] = useState<Record<number, WorkoutItem[]>>({});
    const [workoutName, setWorkoutName] = useState("");

    useEffect(() => {
        exerciseService.getExercises().then((response) => {
            setExercises(response.data.content);
        });
    }, []);

    const addSet = (exerciseId: number) => {
        setSelectedExercises((prev) => ({
            ...prev,
            [exerciseId]: [
                ...(prev[exerciseId] || []),
                { exerciseId, setNumber: (prev[exerciseId]?.length || 0) + 1, reps: '10', rest: 60, weight: 20 },
            ],
        }));
    };

    const updateSet = (exerciseId: number, index: number, key: string, value: number) => {
        setSelectedExercises((prev) => {
            const updatedSets = [...(prev[exerciseId] || [])];
            updatedSets[index] = { ...updatedSets[index], [key]: value };
            return { ...prev, [exerciseId]: updatedSets };
        });
    };

    const saveWorkout = () => {
        const workout: Workout = {
            name: workoutName,
            workoutItems: Object.values(selectedExercises).flat(),
        };

        workoutService.newWorkout(workout).then(() => {
            alert("Treino salvo!");
            setWorkoutName("");
            setSelectedExercises({});
        });
    };

    return (
        <div>
            <h1>Criar Novo Treino</h1>

            <input
                type="text"
                placeholder="Nome do Treino"
                value={workoutName}
                onChange={(e) => setWorkoutName(e.target.value)}
            />

            <h2>Escolha os Exercícios</h2>

            <div>
                {exercises.map((exercise) => (
                    <div key={exercise.id}>
                        <button
                            onClick={() => addSet(exercise.id)}
                        >
                            Adicionar {exercise.name}
                        </button>

                        {selectedExercises[exercise.id]?.map((set, index) => (
                            <div key={index}>
                                <div>
                                    <input
                                        type="number"
                                        value={set.weight}
                                        onChange={(e) => updateSet(exercise.id, index, "weight", +e.target.value)}
                                        placeholder="Peso"
                                    />
                                    <input
                                        type="number"
                                        value={set.reps}
                                        onChange={(e) => updateSet(exercise.id, index, "reps", +e.target.value)}
                                        placeholder="Reps"
                                    />
                                    <input
                                        type="number"
                                        value={set.rest}
                                        onChange={(e) => updateSet(exercise.id, index, "rest", +e.target.value)}
                                        placeholder="Descanso"
                                    />
                                </div>
                            </div>
                        ))}
                    </div>
                ))}
            </div>

            <button
                onClick={saveWorkout}
            >
                Salvar Treino
            </button>
        </div>
    );
}
