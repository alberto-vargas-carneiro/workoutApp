import { WorkoutDTO } from "@/app/models/workout";
import style from './page.module.css';

type WorkoutCardProps = {
    workout: WorkoutDTO;
}

export default function WorkoutCard({ workout }: WorkoutCardProps) {
    return (
        <>
            <div className={style.container}>
                <div className={style.text}>
                    {workout.name}
                </div>
                <span className={style.text}>{workout.date}</span>
            </div >
        </>
    );
}