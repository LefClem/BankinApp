import { apiClient } from "./apiClient";

export async function login(email, password) {
	const response = await apiClient.post(
		"/auth/login",
		{
			email,
			password,
		},
		{
			headers: {
				"Content-Type": "application/json",
			},
		},
	);

	return response.data;
}
