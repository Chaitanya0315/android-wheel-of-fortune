package edu.gatech.seclass.sdpguessit.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.gatech.seclass.sdpguessit.CreatePlayerActivity;
import edu.gatech.seclass.sdpguessit.CreatePuzzleActivity;
import edu.gatech.seclass.sdpguessit.CreateTournamentActivity;
import edu.gatech.seclass.sdpguessit.LoginActivity;
import edu.gatech.seclass.sdpguessit.MainActivity;
import edu.gatech.seclass.sdpguessit.PlayPuzzleActivity;
import edu.gatech.seclass.sdpguessit.PlayTournamentActivity;
import edu.gatech.seclass.sdpguessit.SplashActivity;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);

    void inject(CreatePlayerActivity createPlayerActivity);

    void inject(MainActivity mainActivity);

    void inject(CreatePuzzleActivity createPuzzleActivity);

    void inject(CreateTournamentActivity createTournamentActivity);

    void inject(PlayPuzzleActivity playPuzzleActivity);

    void inject(PlayTournamentActivity tournamentActivity);
}
