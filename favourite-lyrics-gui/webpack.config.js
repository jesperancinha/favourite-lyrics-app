const HtmlWebpackPlugin = require("html-webpack-plugin");
module.exports = {
    plugins: [
        new HtmlWebpackPlugin({
            title: "Favorite Lyrics App",
            template: './public/index.html'
        })
    ],
}