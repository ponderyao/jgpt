package io.github.ponderyao.sdk.jgpt.api;

import java.time.LocalDate;

import io.github.ponderyao.sdk.jgpt.domain.account.AccountBillingUsage;
import io.github.ponderyao.sdk.jgpt.domain.account.AccountSubscription;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * OpenAccountApi: the open api about OpenAI account
 *
 * @author PonderYao
 * @since 1.0.0
 */
public interface OpenAccountApi {

    /**
     * query the account subscription
     *
     * @return account subscription
     */
    @GET("v1/dashboard/billing/subscription")
    Single<AccountSubscription> subscription();

    /**
     * query the account billing usage in a period of time
     *
     * @param startDate the start datetime to query data
     * @param endDate  the end datetime to query data
     * @return account billing usage
     */
    @GET("v1/dashboard/billing/usage")
    Single<AccountBillingUsage> usage(@Query("start_date") LocalDate startDate, @Query("end_date") LocalDate endDate);
    
}
