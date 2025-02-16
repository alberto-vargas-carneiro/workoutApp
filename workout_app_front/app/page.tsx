'use client'

import style from "./page.module.css";
// import { IoEye } from "react-icons/io5";
// import { IoEyeOff } from "react-icons/io5";
import { useState } from "react";
import { CredentialsDTO } from "./models/auth";
import * as authService from "./services/auth-service";

export default function Home() {

  const [formData, setFormData] = useState<CredentialsDTO>({
    username: '',
    password: ''
  })

  function handleSubmit(event: React.FormEvent<EventTarget>) {
    event.preventDefault()
    authService.loginRequest(formData)
      .then(response => {
        console.log('Sucesso:', response.data)
      })
      .catch(error => {
        console.log('Erro:', error.response.data)
      })
  }

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    })
  }

  return (
    <>
      <div className={style.container}>

        <form className={style.formContainer} onSubmit={handleSubmit}>
          <input className={style.form}
            name="username"
            type="email"
            placeholder="username"
            value={formData.username}
            onChange={handleInputChange}
          />
          <input className={style.form}
            name="password"
            type="password"
            placeholder="password"
            value={formData.password}
            onChange={handleInputChange}
          />
          <button className={style.loginButton} type="submit">login</button>
          <span className={style.line}></span>
          <button className={style.loginButton}>login com google</button>
        </form>
      </div>
    </>
  );
}
