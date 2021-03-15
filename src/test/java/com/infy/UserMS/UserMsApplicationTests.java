package com.infy.UserMS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.UserMS.dto.LoginDTO;
import com.infy.UserMS.entity.BuyerEntity;
import com.infy.UserMS.entity.SellerEntity;
import com.infy.UserMS.repository.BuyerRepository;
import com.infy.UserMS.repository.CartRepository;
import com.infy.UserMS.repository.SellerRepository;
import com.infy.UserMS.service.BuyerService;
import com.infy.UserMS.service.SellerService;
import com.infy.validator.Validator;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserMsApplicationTests {
    @Mock
    BuyerRepository buyerRepo;
    
    @Mock
    SellerRepository sellerRepo;
    
    @Mock
    CartRepository cartRepo;
    
    @Autowired 
    BuyerService buyerService;
    
    @Autowired
    SellerService sellerService;
    
    @InjectMocks
    BuyerService buyerServiceMock = new BuyerService();
    
	@Test
	public void checkBuyerByEmailValid() throws Exception {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("raman@gmail.com");
		loginDTO.setPassword("Raman$456");
		assertTrue(buyerService.login(loginDTO));
		
	}
	@Test
	public void checkBuyerByEmailInValid() throws Exception {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("Raman@gmail.com");
		loginDTO.setPassword("Raman456");
		assertFalse(buyerService.login(loginDTO));
		
	}
	@Test
	public void checkRegistrationBuyer() throws Exception{
		BuyerEntity buyer = new BuyerEntity();
		buyer.setBuyerId(15);
		buyer.setEmail("Ajay@gmail.com");
		buyer.setIsActive(1);
		buyer.setIsPrivileged(0);
		buyer.setName("Ajai");
		buyer.setPassword("Ajayyyy$234");
		buyer.setPhonenumber("7766554433");
		buyer.setRewardPoints(2500);
		Mockito.when(buyerRepo.save(buyer)).thenReturn(buyer);
		assertEquals(buyer,buyerRepo.save(buyer));
	}
	@Test
	public void isPrivileged() throws Exception{
		assertFalse(buyerService.privilegedBuyer(1));
	}
	
	@Test
	public void getModifiedAmount() throws Exception{
		int amount = 2000;
		int buyerId = 1;
		double amt = buyerService.getModifiedAmount(amount, buyerId);
		assertEquals(1995 ,(int) amt);
	}
	@Test
	public void checkSellerByEmailValid() throws Exception {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("saurabh@gmail.com");
		loginDTO.setPassword("Saurabh@123");
		assertTrue(sellerService.login(loginDTO));
		
	}
	@Test
	public void checkRegistrationSeller() throws Exception{
		SellerEntity seller = new SellerEntity();
		seller.setSellerId(101);
		seller.setEmail("Raja@gmail.com");
		seller.setName("Rajat");
		seller.setPassword("Rajat$234");
		seller.setPhonenumber("9999999999");
		seller.setIsActive(0);
		Mockito.when(sellerRepo.save(seller)).thenReturn(seller);
		assertEquals(seller,sellerRepo.save(seller));
	}
	@Test
	public void validateName() throws Exception{
		assertTrue(Validator.validateName("Rajat"));
	}
	@Test
	public void validateEmail() throws Exception{
		assertTrue(Validator.validateEmailId("Raja@gmail.com"));
	}
	@Test
	public void validatePhoneNumber() throws Exception{
		assertFalse(Validator.validatePhonenumber("9999899998"));
	}
	@Test
	public void validateEmailId() throws Exception{
		assertTrue(Validator.validatePassword("Rajak#2345"));
	}
	

	
	
	
	
	
	
	

}
