package ebayMock;

import ebayMock.controller.UserController;
import ebayMock.entity.Amount;
import ebayMock.enums.Currency;
import ebayMock.service.EbayService;

import java.math.BigInteger;

public class EbayDriver {
    static void main() {
        UserController userController = UserController.getInstance();
        EbayService ebayService = EbayService.getInstance(userController);
        BigInteger sellerId = new BigInteger("1001");
        BigInteger buyerId = new BigInteger("1002");
        BigInteger item1 = new BigInteger("450");
        BigInteger item2 = new BigInteger("451");
        ebayService.createUser("t1",sellerId,new Amount(Currency.USD,500));
        ebayService.createUser("t2",buyerId,new Amount(Currency.USD,100));
        ebayService.listItem("t2",sellerId,item1,new Amount(Currency.USD,10),50);
        ebayService.listItem("t3",sellerId,item2,new Amount(Currency.USD,5),10);
        ebayService.buy("t4",buyerId,sellerId,item1,5);
        ebayService.buy("t5",buyerId,sellerId,item2,20);
        ebayService.buy("t6",buyerId,sellerId,item1,7);

        System.out.println(userController.getUserById(sellerId).getBalance());
        System.out.println(userController.getUserById(buyerId).getBalance());

    }
}
