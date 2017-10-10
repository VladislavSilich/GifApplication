package com.gif.example.silich.vladislav.giftask;

import com.gif.example.silich.vladislav.giftask.activity.MainActivity;
import com.gif.example.silich.vladislav.giftask.model.GifResponse;
import com.gif.example.silich.vladislav.giftask.viewModel.GifViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.io.IOException;

import io.reactivex.Observable;
import retrofit2.http.Query;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Lenovo on 09.10.2017.
 */
@org.junit.runner.RunWith(JUnit4.class)
public class GifViewModelTest {
    private MainActivity mainActivity;
    private GifViewModel gifViewModel;

    @Before
    public void setup() throws Exception{
        mainActivity = Mockito.mock(MainActivity.class);
        gifViewModel = new GifViewModel(mainActivity);
    }
    @Test
    public void testCreated()throws Exception{
        assertNotNull(gifViewModel);
    }

    @Test
    public void testNoActionsWithView() throws Exception{
        Mockito.verifyNoMoreInteractions(mainActivity);
    }


    public class TestGif extends TestGifSearch{
        @Override
        public Observable<GifResponse> searchGif(@Query("q") String q, @Query("api_key") String key) {
            if ("cat".equals(q) && "BfLUcVTWAl9vVZJxNZNomtth9OY3QBdV".equals(key)){
                return Observable.just(new GifResponse());
            }
            return Observable.error(new IOException());
        }
    }

}
