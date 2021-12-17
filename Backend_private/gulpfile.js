const gulp = require('gulp');
const { series } = require('gulp');
const sass = require('gulp-sass')(require('sass'));

function sassCompilator(cb) {
    return gulp.src('./src/main/resources/static/scss/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest('./src/main/resources/static/css'));
    cb();
}

function copy(cb) {
    return gulp.src('./node_modules/bootstrap/dist/js/*.js')
        .pipe(gulp.dest('./src/main/resources/static/js'));
    cb();
}

exports.sassCompilator = sassCompilator
exports.copy = copy
exports.build = series(copy, sassCompilator)
