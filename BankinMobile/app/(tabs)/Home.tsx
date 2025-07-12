import { StyleSheet } from "react-native";

import EditScreenInfo from "@/components/EditScreenInfo";
import { Text, View } from "@/components/Themed";
import { navigate } from "expo-router/build/global-state/routing";

export default function TabOneScreen() {
	const onPress = () => {
		navigate("/modals/login");
	};
	return (
		<View style={styles.container}>
			<Text style={styles.title}>BankinApp</Text>
			<View
				style={styles.separator}
				lightColor="#eee"
				darkColor="rgba(255,255,255,0.1)"
			/>
			<EditScreenInfo
				path="app/(tabs)/index.tsx"
				buttonTitle={"Login"}
				callBack={onPress}
			/>
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
		fontSize: 30,
		fontWeight: "bold",
	},
	separator: {
		marginVertical: 30,
		height: 1,
		width: "80%",
	},
});
