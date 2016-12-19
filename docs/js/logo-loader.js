(function () {
    var logo = new Vivus('logo', {
        file: './logo1.svg',
        type: 'delayed',
        duration: 2000,
        animTimingFunction: Vivus.EASE
    });
    logo.reset();
    logo.play(0.5);
})();