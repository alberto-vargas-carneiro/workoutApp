'use client'

import { useParams } from 'next/navigation';
import { useEffect, useState } from 'react';
import * as workoutService from '../../services/workout-service';

export default function WorkoutDetailsPage() {

  const { id } = useParams(); // captura o parâmetro da URL
  const [workout, setWorkout] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (id) {
      workoutService.getWorkoutById(Number(id))
        .then(response => {
          setWorkout(response.data);
          setLoading(false);
          console.log(response.data);
        })
        .catch(error => {
          console.error(error);
          setLoading(false);
        });
    }
  }, [id]);

  if (loading) return <div>Carregando...</div>;
  if (!workout) return <div>Workout não encontrado!</div>;

  return (
    <div>
      {/* <h1>{workout}</h1> */}
      {/* Renderize aqui outros detalhes do workout */}
    </div>
  );
}
