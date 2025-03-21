'use client'

import { ExerciseDTO } from '@/app/models/exercise';
import style from './page.module.css';
import { useEffect, useState } from 'react';
import ExerciseCard from '../../cards/exercise-card/page';
import * as exerciseService from '../../services/exercise-service';

export default function ExercisePage() {

    const [exercises, setExercises] = useState<ExerciseDTO[]>([]);

    useEffect(() => {
        exerciseService.getExercises()
            .then(response => {
                setExercises(response.data.content);
            });
    }, []);

    return (
        <div className={style.container}>
            {exercises.map(exercise => <ExerciseCard key={exercise.id} exercise={exercise} />)
            }
        </div>
    );
}