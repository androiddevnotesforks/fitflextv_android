package com.dreamsoftware.fitflextv.domain.exception

open class DomainRepositoryException(message: String? = null, cause: Throwable? = null): Exception(message, cause)
class RepositoryOperationException(message: String? = null, cause: Throwable? = null) : DomainRepositoryException(message, cause)

// Training
class FetchTrainingsException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchTrainingByIdException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchTrainingByCategoryException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchTrainingsRecommendedException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchFeaturedTrainingsException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)

// Categories
class FetchCategoriesException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class FetchCategoryByIdException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)

//Profiles
class FetchProfilesByUserException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class UpdateProfileException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class DeleteProfileException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class CreateProfileException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class VerifyPinException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class SelectProfileException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class GetProfileByIdException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class GetProfileSelectedException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)

// Users
class SignInException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class SignUpException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class SignOffException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class VerifySessionException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class GetUserDetailException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class GetUserAuthenticatedUidException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
class UpdateUserDetailException(message: String? = null, cause: Throwable? = null): DomainRepositoryException(message, cause)
