package itis.animediary.service;

import itis.animediary.data.entity.AnimeEntity;
import itis.animediary.data.entity.AnimeReviewEntity;
import itis.animediary.data.entity.UserEntity;
import itis.animediary.data.repository.AnimeRepository;
import itis.animediary.data.repository.ReviewRepository;
import itis.animediary.data.repository.UserRepository;
import itis.animediary.service.dto.AnimeReviewDto;
import itis.animediary.service.mappers.review.AnimeReviewListMapper;
import itis.animediary.service.mappers.review.AnimeReviewMapper;
import itis.animediary.service.requestdto.ReviewForm;
import itis.animediary.utils.properties.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;

    private final AnimeReviewListMapper animeReviewListMapper;
    private final AnimeReviewMapper animeReviewMapper;

    public List<AnimeReviewDto> getAllReviewsByAnimeId(long id) {
        AnimeEntity anime = animeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.ANIME_NOT_FOUND_ERROR));
        List<AnimeReviewEntity> reviews = reviewRepository.findAllByAnime(anime);
        return animeReviewListMapper.toDTO(reviews);
    }

    public Optional<AnimeReviewDto> getReviewByAnimeIdAndUserId(long animeId, long userId) {
        AnimeEntity anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.ANIME_NOT_FOUND_ERROR));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND_ERROR));

        return reviewRepository.findByAnimeAndUser(anime, user).map(animeReviewMapper::toDTO);
    }

    public void saveOrUpdate(long userId, long animeId, ReviewForm reviewForm) {
        AnimeEntity anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.ANIME_NOT_FOUND_ERROR));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND_ERROR));
        Optional<AnimeReviewEntity> existingOptional = reviewRepository.findByAnimeAndUser(anime, user);
        if (existingOptional.isPresent()) {
            AnimeReviewEntity existing = existingOptional.get();
            existing.setRateDrawing(reviewForm.getRateDrawing());
            existing.setRatePlot(reviewForm.getRatePlot());
            existing.setRateCharacters(reviewForm.getRateCharacters());
            existing.setRateOpening(reviewForm.getRateOpening());
            existing.setReviewText(reviewForm.getReviewText());
            reviewRepository.save(existing);
        } else {
            AnimeReviewEntity newReview = AnimeReviewEntity.builder()
                    .anime(anime)
                    .user(user)
                    .rateDrawing(reviewForm.getRateDrawing())
                    .ratePlot(reviewForm.getRatePlot())
                    .rateCharacters(reviewForm.getRateCharacters())
                    .rateOpening(reviewForm.getRateOpening())
                    .resultScore((reviewForm.getRateDrawing() + reviewForm.getRatePlot() + reviewForm.getRateCharacters() + reviewForm.getRateOpening())/4.0)
                    .reviewText(reviewForm.getReviewText())
                    .build();
            reviewRepository.save(newReview);
        }
    }

    public void deleteReview(long reviewId) {
        AnimeReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.REVIEW_NOT_FOUND_ERROR));
        reviewRepository.delete(review);
    }

    //для REST-контроллера
    public Optional<AnimeReviewDto> getReviewById(long reviewId) {
        return reviewRepository.findById(reviewId).map(animeReviewMapper::toDTO);
    }
}
