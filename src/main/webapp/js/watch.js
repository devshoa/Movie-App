var myFP = fluidPlayer("video", {
    layoutControls: {
        controlBar: {
            autoHideTimeout: 3,
            animated: true,
            autoHide: true,
        },
        htmlOnPauseBlock: {
            html: null,
            height: null,
            width: null,
        },
        autoPlay: false,
        mute: true,
        allowTheatre: true,
        playPauseAnimation: true,
        playbackRateEnabled: true,
        allowDownload: true,
        playButtonShowing: true,
        fillToContainer: false,
        posterImage: "poster.jpg",
    },
    vastOptions: {
        adList: [],
        adCTAText: false,
        adCTATextPosition: "",
    },
});