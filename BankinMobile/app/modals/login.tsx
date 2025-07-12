import { Text, View } from "@/components/Themed";
import { StatusBar } from "expo-status-bar";
import { Controller, useForm } from "react-hook-form";
import { Button, Platform, StyleSheet, TextInput } from "react-native";
import { login } from "../api/auth.js";

export default function ModalScreen() {
	const {
		control,
		handleSubmit,
		formState: { errors },
	} = useForm();

	// biome-ignore lint/suspicious/noExplicitAny: <explanation>
	const onSubmit = async (data: any) => {
		const payload = {
			email: data.email,
			password: data.password,
		};
		console.log(JSON.stringify(payload, null, 2));
		console.log(payload.email, payload.password);

		try {
			const result = await login(payload.email, payload.password);
			console.log(result);
		} catch (error) {
			console.error(error);
		}
	};

	return (
		<View style={styles.container}>
			<Text style={styles.title}>Modal</Text>
			<Controller
				control={control}
				name="email"
				rules={{ required: true }}
				render={({ field: { onChange, value } }) => (
					<TextInput onChangeText={onChange} value={value} />
				)}
			/>
			<Controller
				control={control}
				name="password"
				rules={{ required: true }}
				render={({ field: { onChange, value } }) => (
					<TextInput onChangeText={onChange} value={value} />
				)}
			/>
			<Button title="connexion" onPress={handleSubmit(onSubmit)} />

			{/* Use a light status bar on iOS to account for the black space above the modal */}
			<StatusBar style={Platform.OS === "ios" ? "light" : "auto"} />
		</View>
	);
}

const styles = StyleSheet.create({
	container: {
		flex: 1,
		alignItems: "center",
		justifyContent: "center",
	},
	title: {
		fontSize: 20,
		fontWeight: "bold",
	},
	separator: {
		marginVertical: 30,
		height: 1,
		width: "80%",
	},
});
