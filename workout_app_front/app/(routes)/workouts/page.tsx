'use client'

import { useEffect, useState } from 'react';
import * as userService from '../../services/user-service';
import style from './page.module.css';
import { WorkoutDTO } from '../../models/workout';
import WorkoutCard from '../../cards/workout-cards/workout/page';
import Link from 'next/link';

export default function WorkoutPage() {

  const [loading, setLoading] = useState(true)
  const [workouts, setWorkouts] = useState<WorkoutDTO[]>([]);

  useEffect(() => {
    userService.findLoggedUser()
      .then(response => {
        setWorkouts(response.data.workouts);
        console.log(response.data)
        setLoading(false);
      })
      .catch(error => {
        console.log(error);
        setLoading(false);
      });
  }, []);

  if (loading) return <div>Carregando...</div>;

  if (!workouts?.length) {
    return (
      <div className={style.container}>

        <span className={style.vazio}>Aqui está vazio!</span>
        <span className={style.vazio}>Comece a mudança agora mesmo!</span>
        <Link href={'/workout-creation'}>
          <div className={style.add2}>+</div>
        </Link>
        <button className={style.add}>+</button>
      </div>
    );
  }

  return (
    <div className={style.container2}>
      <div className={style.workout_container}>
        {workouts.map(workout => (
          <>
            <WorkoutCard key={workout.id} workout={workout} />
          </>
        ))}
      </div>
      <div className={style.add2_container}>
        <Link href={'/workout-creation'}>
          <span className={style.add2}>+</span>
          {/* <button className={style.add2}>+</button> */}
        </Link>
      </div>
    </div>
  );
};
