package com.plumillonforge.ewa.presentation.asyncs;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.plumillonforge.ewa.domain.entities.MusicEntity;
import com.plumillonforge.ewa.domain.mappers.Mapper;
import com.plumillonforge.ewa.domain.useCases.UseCase;
import com.plumillonforge.ewa.presentation.models.MusicModel;

/**
 * Created by Flavien Norindr
 */
public class GetRandomMusicAsyncTask extends AsyncTask<Void, Void, MusicModel> {
    public interface GetMusicsAsyncTaskListener {
        void onSuccess(MusicModel music);

        void onError(Throwable throwable);
    }

    private UseCase<MusicEntity> useCase;
    private Mapper<MusicEntity, MusicModel> mapper;
    private GetMusicsAsyncTaskListener listener = null;

    public GetRandomMusicAsyncTask(@NonNull UseCase<MusicEntity> useCase, @NonNull Mapper<MusicEntity, MusicModel> mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    protected MusicModel doInBackground(Void... voids) {
        return mapper.map(useCase.execute());
    }

    @Override
    protected void onPostExecute(MusicModel result) {
        if (listener != null) {
            if (result != null) {
                listener.onSuccess(result);
            }
        }
    }

    public void setListener(GetMusicsAsyncTaskListener listener) {
        this.listener = listener;
    }
}
