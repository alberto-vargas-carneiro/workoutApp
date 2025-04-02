import style from './page.module.css';
import { FaArrowLeft } from "react-icons/fa";
import WorkoutItemsCard from '../items/page';

interface DetailsCardProps {
    title: string;
    name: string;
    set: number;
    weight: number;
    reps: string;
}

export default function WorkoutDetailsCard(props: DetailsCardProps) {
    return (
        <div className={style.container}>
            <div className={style.title}>
                <FaArrowLeft className={style.arrow} />
                <h1>Peito</h1>
            </div>
            <div className={style.items_container}>
                <WorkoutItemsCard name={props.name}/>
            </div>
        </div>
    );
}