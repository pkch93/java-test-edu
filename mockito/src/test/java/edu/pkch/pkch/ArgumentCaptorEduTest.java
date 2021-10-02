package edu.pkch.pkch;

import edu.pkch.Request;
import edu.pkch.RequestClient;
import edu.pkch.RequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ArgumentCaptorEduTest {

    @InjectMocks
    private RequestService requestService;

    @Mock
    private RequestClient requestClient;

    @Test
    void argumentCaptor() {
        // given
        willDoNothing().given(requestClient).request(any(Request.class));

        // when
        requestService.requestActive("pkch");

        // then
        ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
        then(requestClient).should().request(requestCaptor.capture());

        Request actualRequest = requestCaptor.getValue();
        assertThat(actualRequest.getStatus()).isEqualTo(Request.Status.ACTIVE);
    }

    @Test
    void argumentCaptor_multipleValueCapture() {
        // given
        willDoNothing().given(requestClient).request(any(Request.class));

        // when
        requestService.requestAllStatus("pkch");

        // then
        ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
        then(requestClient).should(times(2)).request(requestCaptor.capture());

        assertThat(requestCaptor.getAllValues()).hasSize(2)
                .extracting(Request::getStatus)
                .containsExactly(Request.Status.ACTIVE, Request.Status.WITHDRAW);
    }

    @Test
    void argumentCaptor_multipleValueCapture_getValue() {
        // given
        willDoNothing().given(requestClient).request(any(Request.class));

        // when
        requestService.requestAllStatus("pkch");

        // then
        ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
        then(requestClient).should(times(2)).request(requestCaptor.capture());

        Request request = requestCaptor.getValue();
        assertThat(request.getStatus()).isEqualTo(Request.Status.WITHDRAW);
    }
}
