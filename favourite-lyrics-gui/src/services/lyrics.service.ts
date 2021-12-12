import * as axios from "axios";
import {AxiosInstance} from "axios";
import {Lyric} from "../domain/lyric";

export default class LyricsService {
    private client: AxiosInstance | null;

    constructor() {
        this.client = null;
    }

    init = () => {
        let headers = {
            Accept: "application/json",
        };

        this.client = axios.default.create({
            baseURL: process.env.REACT_APP_API_ENDPOINT,
            timeout: 31000,
            headers: headers,
        });

        return this.client;
    };

    getRandomLyric = () => {
        return this.init().get<string>("/lyrics/random");
    };

    getAllLyrics = () => {
        return this.init().get<Lyric[]>("/lyrics");
    };

}