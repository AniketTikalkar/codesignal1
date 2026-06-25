package ebayMock.service;

import ebayMock.controller.UserController;
import ebayMock.entity.Amount;
import ebayMock.entity.Listing;
import ebayMock.entity.User;

import java.math.BigInteger;


public class EbayService {
    private final UserController userController;
    private static  EbayService INSTANCE;
    private EbayService(UserController userController) {
        this.userController = userController;
    }
    public static EbayService getInstance(UserController userController){
        if(INSTANCE == null){
            return new EbayService(userController);
        }
        return INSTANCE;
    }

    public boolean createUser(String timeStamp, BigInteger userId, Amount balance){
        if(userController.doesUserExist(userId)){
            System.out.println("User already exist");
            return false;
        }
        User user = new User(userId,timeStamp,balance);
        userController.addUserById(userId,user);
        System.out.println(String.format("User %s created",userId));
        return true;
    }

    public boolean listItem(String timeStamp, BigInteger userId, BigInteger itemId, Amount price, int quantity ){
        if(userController.isItemListedByUser(userId, itemId)){
            System.out.println("Item already listed");
            return false;
        }
        Listing listing = new Listing(itemId,quantity,price,timeStamp);
        userController.addItemForUser(userId, listing);
        System.out.println(String.format("Item %s listed by %s",itemId,userId));
        return true;
    }

    public Amount buy(String timestamp,BigInteger buyerId, BigInteger sellerId, BigInteger itemId, int quantity){
        if(!userController.doesUserExist(sellerId) || !userController.doesUserExist(buyerId) || !userController.isItemListedByUser(sellerId,itemId)){
            System.out.println("User or Listing does not exist");
            return null;
        }
        int availableQuantity = userController.getListingByUser(sellerId,itemId).getQuantity();
        int requestedQuantity = quantity;
        User buyer = userController.getUserById(buyerId);
        User seller = userController.getUserById(sellerId);
        Listing listing = userController.getListingByUser(sellerId,itemId);
        int total = quantity*listing.getUnitPrice().getAmount();
        if(requestedQuantity > availableQuantity || buyer.getBalance().getCurrency() != seller.getBalance().getCurrency() || total > buyer.getBalance().getAmount()){
            System.out.println("Cant fulfil request");
            return null;
        }

        listing.setQuantity(availableQuantity-quantity);
        buyer.updateBalanceAmount(buyer.getBalance().getAmount() - total);
        seller.updateBalanceAmount(seller.getBalance().getAmount() + total);
        return new Amount(buyer.getBalance().getCurrency(),total);
    }
}
