package repositories;

import vo.Account;

/**
 * Created by hyhe on 4/29/15.
 */
public interface AccountRepository {
    Account findAccount(int id);
}
