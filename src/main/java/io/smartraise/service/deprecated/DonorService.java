package io.smartraise.service.deprecated;

import io.smartraise.model.accounts.Donor;
import io.smartraise.service.skeleton.AccountCrudService;
import io.smartraise.service.skeleton.ValidationService;

/**
 * Service for the {@link Donor} model
 */
@Deprecated
public interface DonorService extends AccountCrudService<Donor>, ValidationService<Donor> {
}
