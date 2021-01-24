package com.pgilewsk.service.mvc;

import com.pgilewsk.service.domain.PersonDto;
import com.pgilewsk.service.service.PersonService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonService personService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    void addPerson() {
        //given
        PersonDto personDto = new PersonDto(1, "Anna", "Kowalska");
        //when
        ResponseEntity<Integer> response = restTemplate.exchange(createURLWithPort("/persons"), HttpMethod.POST, new HttpEntity<>(personDto, null), Integer.class);
        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldFindPersonByIdAndReturnPersonDto() {
        //given
        when(personService.findById(1)).thenReturn(getPersonExample());
        //when
        ResponseEntity<Optional<PersonDto>> response = restTemplate.exchange(createURLWithPort("/dtos/1"), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isPresent());
        response.getBody().ifPresent(reply -> {
            assertEquals(1, reply.getId());
            assertEquals("Anna", reply.getFirstName());
            assertEquals("Kowalska", reply.getLastName());
        });
    }

    @Test
    void shouldThrowAnExceptionWhenPersonDtoDoesNotExist() throws HttpClientErrorException {
        thrown.expect(HttpClientErrorException.class);
        restTemplate.exchange(createURLWithPort("/dtos/1"), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
    }


    @Test
    void shouldFindPersonByIdAndReturnString() {
        //given
        when(personService.findPersonById(1)).thenReturn(getPersonString());
        String expectedResponse = "person { id = 1, firstName = Anna, lastName = Kowalska }\n";
        //when
        ResponseEntity<Optional<String>> response = restTemplate.exchange(createURLWithPort("/persons/1"), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        response.getBody().ifPresent(reply -> assertEquals(expectedResponse, reply));
    }

    @Test
    void shouldThrowAnExceptionWhenStringDoesNotExist() throws HttpClientErrorException {
        thrown.expect(HttpClientErrorException.class);
        restTemplate.exchange(createURLWithPort("/persons/1"), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
    }

    @Test
    void shouldFindAllPersonsAndReturnListWithPersonDto() {
        //given
        when(personService.findAll()).thenReturn(getPersonExamples());
        //when
        ResponseEntity<ArrayList<PersonDto>> response = restTemplate.exchange(createURLWithPort("/dtos"), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(1, response.getBody().get(0).getId());
        assertEquals("Anna", response.getBody().get(0).getFirstName());
        assertEquals("Nowacki", response.getBody().get(1).getLastName());
    }

    @Test
    void shouldFindAllPersonsAndReturnString() throws Exception {
        //given
        when(personService.findAllPersons()).thenReturn(getPersonStings());
        String expectedResponse = "person { id = 1, firstName = Anna, lastName = Kowalska }\n" +
                "person { id = 2, firstName = Hubert, lastName = Nowacki }";
        //when
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/persons"), HttpMethod.GET, null, String.class);
        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedResponse, response.getBody());
    }

    private Optional<String> getPersonString() {
        return Optional.of("person { id = 1, firstName = Anna, lastName = Kowalska }\n");
    }

    private String getPersonStings() {
        return "person { id = 1, firstName = Anna, lastName = Kowalska }\n" +
                "person { id = 2, firstName = Hubert, lastName = Nowacki }";
    }

    private Optional<PersonDto> getPersonExample() {
        return Optional.of(new PersonDto(1, "Anna", "Kowalska"));
    }

    private List<PersonDto> getPersonExamples() {
        List<PersonDto> personDtos = new ArrayList<>();
        personDtos.add(new PersonDto(1, "Anna", "Kowalska"));
        personDtos.add(new PersonDto(2, "Hubert", "Nowacki"));
        return personDtos;
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}