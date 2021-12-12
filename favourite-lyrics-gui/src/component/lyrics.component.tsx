import ReactDOM from "react-dom";
import React from "react";
import LyricsService from "../services/lyrics.service";
import {Lyric} from "../domain/lyric";


class LyricsComponent extends React.Component<any, any> {
    private lyricsService = new LyricsService()

    constructor(props: any) {
        super(props);
        this.state = {
            data: {
                lyrics: [],
                random: []
            },
        };
        this.lyricsService.getAllLyrics().then(allLyrics => {

            this.lyricsService.getRandomLyric().then(randomLyric => {
                let data = {
                    lyrics: allLyrics.data,
                    random: [randomLyric.data]
                };
                console.log(data)
                console.log(randomLyric)
                this.setState({
                    data: data
                })
            })
        });
    }

    render() {
        return (<div>
            <div><h1 style={{color: "greenyellow", textAlign: "center"}}>Favourite Lyrics - Hexagonal Architecture Java
                17 Example</h1></div>
            {this.state.data.lyrics.map((lyric: Lyric) => (
                <div style={{background: "darkgreen", textAlign: "center"}}>
                    <p>Lyric : {lyric.lyrics}</p>
                    <p>Participating Artis : {lyric.participatingArtist}</p>
                </div>
            ))}
            <div><h3 style={{color: "greenyellow", textAlign: "center"}}>Random Lyric</h3></div>
            {this.state.data.random.map((lyric: Lyric) => (
                <div style={{background: "forestgreen", textAlign: "center", color: "greenyellow"}}>
                    <p>Lyric : {lyric.lyrics}</p>
                    <p>Participating Artis : {lyric.participatingArtist}</p>
                </div>
            ))}
        </div>);
    }
}

ReactDOM.render(
    <LyricsComponent/>,
    document.getElementById('root')
);

export default LyricsComponent