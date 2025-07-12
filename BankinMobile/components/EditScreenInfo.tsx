import React from "react";
import { Button, StyleSheet } from "react-native";

import { View } from "./Themed";

type Props = {
	path: string;
	buttonTitle: string;
	callBack?: () => void;
};

export default function EditScreenInfo({ path, buttonTitle, callBack }: Props) {
	return (
		<View>
			<Button
				title={buttonTitle}
				onPress={() => {
					callBack ? callBack() : console.log("error");
				}}
			/>
		</View>
	);
}

const styles = StyleSheet.create({
	getStartedContainer: {
		alignItems: "center",
		marginHorizontal: 50,
	},
	homeScreenFilename: {
		marginVertical: 7,
	},
	codeHighlightContainer: {
		borderRadius: 3,
		paddingHorizontal: 4,
	},
	getStartedText: {
		fontSize: 17,
		lineHeight: 24,
		textAlign: "center",
	},
	helpContainer: {
		marginTop: 15,
		marginHorizontal: 20,
		alignItems: "center",
	},
	helpLink: {
		paddingVertical: 15,
	},
	helpLinkText: {
		textAlign: "center",
	},
});
