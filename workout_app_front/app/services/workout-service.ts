import { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";
import * as authService from "./auth-service";

export function getWorkoutById(id: number) {

    const headers = {
        Authorization: "Bearer " + authService.getAccessToken()
    }

    const config: AxiosRequestConfig = {
        url: `/workouts/${id}`,
        headers
    }

    return requestBackend(config);
}