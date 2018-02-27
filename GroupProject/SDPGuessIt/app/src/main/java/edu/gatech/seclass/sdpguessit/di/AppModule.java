package edu.gatech.seclass.sdpguessit.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.gatech.seclass.sdpguessit.data.managers.PlayerManager;
import edu.gatech.seclass.sdpguessit.data.managers.PuzzleManager;
import edu.gatech.seclass.sdpguessit.data.managers.TournamentManager;

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    PlayerManager providesPlayerManager(Context context) {
        return new PlayerManager(context);
    }

    @Provides
    @Singleton
    PuzzleManager providesPuzzleManager() {
        return new PuzzleManager();
    }

    @Provides
    @Singleton
    TournamentManager providesTournamentManager() {
        return new TournamentManager();
    }
}
