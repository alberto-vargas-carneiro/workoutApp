import style from './page.module.css';

export default function WorkoutPage() {

    return (
        <div className={style.container}>

            <span className={style.vazio}>Aqui está vazio!</span>
            <span className={style.vazio}>Comece a mudança agora mesmo!</span>
            <button className={style.add}>+</button>

        </div>
    );
};
