import style from './page.module.css';
import { ExerciseDTO } from '@/app/models/exercise';

type ExerciseCardProps = {
    exercise: ExerciseDTO;
}

export default function ExerciseCard({ exercise }: ExerciseCardProps) {
    return (
        <>
            <div className={style.container}>
                <div className={style.image}>
                    {exercise.video}
                </div>
                <span className={style.text}>{exercise.name}</span>
            </div >
        </>
    );
}