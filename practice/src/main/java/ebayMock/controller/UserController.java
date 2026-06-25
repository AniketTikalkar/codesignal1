package ebayMock.controller;


import ebayMock.entity.Listing;
import ebayMock.entity.User;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


//singleton
public class UserController {
    private final Map<BigInteger, User> userMap = new ConcurrentHashMap<>();
    private final Map<BigInteger, List<Listing>> userItemMap = new ConcurrentHashMap<>();

    private UserController() {

    }
    private static class UserHelper{
        private static final UserController INSTANCE = new UserController();
    }
    public static UserController getInstance(){
        return UserHelper.INSTANCE;
    }
    public boolean doesUserExist(BigInteger userId){
        return userMap.containsKey(userId);
    }
    public void addUserById(BigInteger userId,User user){
        userMap.putIfAbsent(userId,user);
    }
    public void removeUserById(BigInteger userId){
        userMap.remove(userId);
    }
    public User getUserById(BigInteger userId){
        return userMap.getOrDefault(userId,null);
    }
    public Listing getListingByUser(BigInteger userId,BigInteger itemId){
        if(!isItemListedByUser(userId, itemId)){
            throw new IllegalCallerException("No Listing found");
        }
        return userItemMap.get(userId).stream().filter(i -> itemId.equals(i.getItemId())).findFirst().get();
    }

    public boolean isItemListedByUser(BigInteger userId,BigInteger itemId){
        return userItemMap.containsKey(userId) && userItemMap.get(userId).stream().anyMatch(listing -> itemId.equals(listing.getItemId()));
    }

    public void addItemForUser(BigInteger userId, Listing listing){
        if(!isItemListedByUser(userId, listing.getItemId())){
            userItemMap.compute(userId,(k,v)->{
                if(v == null){
                    v = new ArrayList<>(List.of(listing));
                }
                else{
                    v.add(listing);
                }
                return v;
            });
        }
    }
}
