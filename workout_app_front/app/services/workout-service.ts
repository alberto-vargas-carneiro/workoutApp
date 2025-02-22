import { AxiosRequestConfig } from "axios";
import { requestBackend } from "../utils/requests";
import * as authService from "./auth-service";

export function getWorkouts(UserId: number) {

    const headers = {
        Authorization: "Bearer " + authService.getAccessToken()
    }

    const config: AxiosRequestConfig = {
        url: `/workouts/user/${UserId}`,
        headers
    }

    return requestBackend(config);
}