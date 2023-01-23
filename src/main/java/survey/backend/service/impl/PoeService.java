package survey.backend.service.impl;


import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.dto.TraineeDto;
import survey.backend.repository.TraineeRepository;
import survey.backend.repository.entities.Poe;
import survey.backend.repository.PoeRepository;
import survey.backend.repository.entities.Trainee;
import survey.backend.util.StreamUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PoeService implements survey.backend.service.PoeService {

    @Autowired
    PoeRepository poeRepository;

    @Autowired
    TraineeRepository traineeRepository;

    @Autowired
    ModelMapper modelMapper;

    @Value("${mail.api}")
    private String MJPublic;

    @Value("${mail.secret}")
    private String MJSecret;

    @Value("${mail.sender}")
    private String Sender;

    MailjetRequest request;
    MailjetResponse response;

    public Collection<PoeFullDto> findAll() {
        return StreamUtils.toStream(poeRepository.findAll())
                .map(poeEntity -> modelMapper.map(poeEntity, PoeFullDto.class))
                .toList();
    }

    @Override
    public Optional<PoeFullDto> findById(long id) {
        return poeRepository.findById(id)
                .map(poeEntity -> modelMapper.map(poeEntity, PoeFullDto.class));
    }

    @Override
    public PoeDto add(PoeDto poeDto) {
        Poe poeEntity = modelMapper.map(poeDto, Poe.class);
        poeRepository.save(poeEntity);
        return modelMapper.map(poeEntity, PoeDto.class);
    }

    @Override
    public Optional<PoeFullDto> update(PoeDto poeDto) {
        return poeRepository.findById(poeDto.getId())
                .map(poeEntity -> {
                    modelMapper.map(poeDto, poeEntity);
                    poeRepository.save(poeEntity);
                    return modelMapper.map(poeEntity, PoeFullDto.class);
                });
    }


    @Override
    public Optional<PoeFullDto> addTrainee(long poeId, long traineeId) {
        return poeRepository.findById(poeId)
                .flatMap(poeEntity -> traineeRepository.findById(traineeId)
                        .map(traineeEntity -> {
                            // add trainee to poe
                            poeEntity.getTrainees().add(traineeEntity);
                            // sync with DB
                            poeRepository.save(poeEntity);
                            // return poe updated
                            return modelMapper.map(poeEntity, PoeFullDto.class);
                        })
                );
    }

    @Override
    public Optional<PoeFullDto> addTrainees(long poeId, Collection<Long> traineeIds) {
        return poeRepository.findById(poeId)
                .flatMap(poeEntity -> {
                    var traineeEntities = StreamUtils.toStream(traineeRepository.findAllById(traineeIds))
                            .toList();
                    if (traineeIds.size() != traineeEntities.size()) {
                        return Optional.empty();
                    }
                    // add trainees in poe
                    poeEntity.getTrainees().addAll(traineeEntities);
                    // sync with DB
                    poeRepository.save(poeEntity);
                    // return poe updated as DTO
                    return Optional.of(modelMapper.map(poeEntity, PoeFullDto.class));
                });
    }

    @Override
    public Optional<PoeFullDto> removeTrainee(long poeId, long traineeId) {
        return this.poeRepository.findById(poeId)
                .flatMap(poeEntity -> {
                    Optional<Trainee> optTrainee = this.traineeRepository.findById(traineeId);
                    if (optTrainee.isEmpty()) {return Optional.empty();}
                    poeEntity.getTrainees().remove(optTrainee.get());
                    this.poeRepository.save(poeEntity);
                    return Optional.of(modelMapper.map(poeEntity, PoeFullDto.class));
                });
    }

    @Override
    public Optional<PoeFullDto> clearTrainees(long poeId) {
        return this.poeRepository.findById(poeId)
                .flatMap(poeEntity -> {
                    poeEntity.getTrainees().clear();
                    this.poeRepository.save(poeEntity);
                    return Optional.of(modelMapper.map(poeEntity, PoeFullDto.class));
                });
    }

    @Override
    public boolean remove(long poeId){
        return poeRepository.findById(poeId)
                .map(poeEntity -> {
                    poeRepository.delete(poeEntity);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public MailjetResponse mail(long poeId, long survey_id) throws MailjetException {
        // Create optional of trainees included in poe to mail
        Optional<List<TraineeDto>> traineesDto = this.poeRepository.findById(poeId)
                .flatMap(poeEntity -> {
                    var traineeEntities = StreamUtils.toStream(poeEntity.getTrainees()).toList();
                    if (traineeEntities.isEmpty()) {return Optional.empty();}
                    var trainees = traineeEntities
                            .stream()
                            .map(trainee -> this.modelMapper.map(trainee, TraineeDto.class))
                            .toList();
                    return Optional.of(trainees);
                });
        // If trainees != 0, send mail to each trainee
        if (traineesDto.isPresent()) {
            List<TraineeDto> trainees = traineesDto.get();
            trainees.forEach(trainee -> {
                ClientOptions options = ClientOptions.builder()
                        .apiKey(MJPublic)
                        .apiSecretKey(MJSecret)
                        .build();
                MailjetClient client = new MailjetClient(options);
                request = new MailjetRequest(Emailv31.resource)
                        .property(Emailv31.MESSAGES, new JSONArray()
                                .put(new JSONObject()
                                        .put(Emailv31.Message.FROM, new JSONObject()
                                                .put("Email", Sender)
                                                .put("Name", "Geoffrey from the back"))
                                        .put(Emailv31.Message.TO, new JSONArray()
                                                .put(new JSONObject()
                                                        .put("Email", trainee.getEmail())
                                                        .put("Name", "You")))
                                        .put(Emailv31.Message.SUBJECT, "Suivi post-stagiaire")
                                        .put(Emailv31.Message.TEXTPART, "Greetings from Mailjet!")
                                        .put(Emailv31.Message.HTMLPART,
                                                "<img src=\"https://media.licdn.com/dms/image/C4D0BAQGj8GUDOoaB0g/company-logo_200_200/0/1587987303565?e=2147483647&v=beta&t=i-CCnedSuyst6egrg_8fJrGYe2YmlAGfR2VUECVk7iw\">"+
                                                        "<h3>Cher stagiaire, merci de bien vouloir vous inscrire au nouveau site de suivi post-stagiaire <a href=\"http://localhost:4200/\">Suivi post-stagiaire Aelion</a>!</body><br />En attente de vous retrouver !")));
                try {
                    response = client.post(request);
                } catch (MailjetException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(response.getStatus());
                System.out.println(response.getData());

//                if (response.getStatus() == 200) {
//                    poeRepository.findById(poeId);
//                }
            });
        }
        return response;
    }



}