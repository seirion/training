import React from 'react';
import { ImageBackground, StyleSheet, Text, TextInput, View } from 'react-native';
import Forecast from "./Forecast";
import OpenWeatherMap from "./open_weather_map"

export default class WeatherProject extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            zip: "",
            forecast: null
        }
    }

    _handleTextChange = event => {
        console.log(`_handleTextChange: ${event.nativeEvent.text}`)
        let zip = event.nativeEvent.text;
        OpenWeatherMap.fetchForecast(zip)
            .then(forecast => {
                console.log(forecast);
                this.setState({forecast: forecast});
            })
    };

    render() {
        let content = null;
        if (this.state.forecast != null) {
            content = (
                <Forecast
                    main={this.state.forecast.main}
                    description={this.state.forecast.description}
                    temp={this.state.forecast.temp}
                />
            )
        }
        return (
            <View style={styles.container}>
                <ImageBackground
                    source={require("./res/flowers.png")}
                    style={styles.backDrop}
                >
                    <View style={styles.overlay}>
                        <View style={styles.row}>
                            <Text style={styles.mainText}>
                                Current Weather for
                            </Text>
                            <View style={styles.zipContainer}>
                                <TextInput
                                    style={[styles.zipCode, styles.mainText]}
                                    onSubmitEditing={this._handleTextChange}
                                />
                            </View>
                        </View>
                        {content}
                    </View>
                </ImageBackground>
            </View>
        );
    }
}

const baseFontSize = 16;

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#666666',
        justifyContent: 'center',
    },
    backDrop: {
        flex: 1,
        flexDirection: "column",
        resizeMode: "cover"
    },
    overlay: {
        paddingTop: 5,
        backgroundColor: "black",
        opacity: 0.5,
        flexDirection: "column",
        alignItems: "center"
    },
    row: {
        flexDirection: "row",
        flexWrap: "nowrap",
        alignItems: "flex-start",
        padding: 30
    },
    zipContainer: {
        height: baseFontSize + 10,
        borderBottomColor: "#dddddd",
        borderBottomWidth: 1,
        marginLeft: 5,
        marginRight: 3
    },
    zipCode: {
        flex: 1,
        flexBasis: 1,
        width: 50,
        height: baseFontSize
    },
    mainText: {
       fontSize: baseFontSize,
       color: "white"
    }
});
