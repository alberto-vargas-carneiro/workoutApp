import style from './page.module.css';
import { FaArrowLeft } from "react-icons/fa";

export default function WorkoutDetailsCard() {
    return (
        <div className={style.container}>
            <div className={style.title}>
                <FaArrowLeft className={style.arrow} />
                <h1>Peito</h1>
            </div>
            <div className={style.items_container}>
                <div>a</div>
            </div>
        </div>
    );
}