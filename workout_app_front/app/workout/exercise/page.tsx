'use client'

import { ExerciseDTO } from '@/app/models/exercise';
import style from './page.module.css';
import { useEffect, useState } from 'react';
import { BASE_URL } from '@/app/utils/system';
import axios from 'axios';
import ExerciseCard from './exercise-card/page';

export default function ExercisePage() {

    const [exercises, setExercises] = useState<ExerciseDTO[]>([]);

    useEffect(() => {
        axios.get(BASE_URL + '/exercises')
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