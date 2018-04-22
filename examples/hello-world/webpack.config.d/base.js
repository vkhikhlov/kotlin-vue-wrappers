var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');

function resolve(dir) {
    return path.join(__dirname, dir);
}

config.mode = 'development';

Object.assign(config.resolve, {
    alias: {
        'vue$': 'vue/dist/vue.common.js'
    }
});

config.plugins = [
    new HtmlWebpackPlugin({
        filename: 'index.html',
        template: resolve('../src/main/web/index.html'),
        inject: true
    })
];