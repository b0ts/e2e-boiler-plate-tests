//package sequences;
//
//import io.appium.java_client.AppiumDriver;
//import lib.AppiumDriverUtil;
//import appium_pages.*;
//
//import java.io.IOException;
//
//public class Boiler2Sequences {
//    private AppiumDriver appiumDriver;
//    private String phoneType;
//    public AppiumDriverUtil util;
//    private SplashPage splash;
//    private LoginPage loginPage;
//    private PrivacyPolicyPage privacyPolicyPage;
////    private CoachWelcomePage coachWelcome;
////    private PrivacyPage privacyPage;
////    private AddADevicePage addPage;
////    private FeedPage feedPage;
////    private ExplorePodsPage explorePodsPage;
////    private ExplorePodsFilterPage explorePodsFilterPage;
////    private GrantAccessPage grantAccessPage;
////    private PairEraPage pairEraPage;
////    private PairedPage pairedPage;
////    private StayConnectedPage stayConnectedPage;
////    private GetToKnowEraPage getToKnowEraPage;
////    private ProductUsagePage productUsagePage;
////    private FirmwareUpdatePage firmwareUpdatePage;
////    private FirmwareUpdatingPage firmwareUpdatingPage;
////    private FirmwareCompletePage firmwareCompletePage;
////    private DevicePropertiesPage devicePropertiesPage;
//
//    public Boiler2Sequences(AppiumDriver appiumDriver) {
//        this.driver = appiumDriver;
//        this.phoneType = phoneType;
//        util = new AppiumDriverUtil(appium.driver, phoneType);
//        splash = new SplashPage(appiumDriver);
//        loginPage = new LoginPage(appiumDriver);
//        privacyPolicyPage = new PrivacyPolicyPage(appiumDriver);
//    }
//
//    public void navigateToLoginPage(boolean privacy) {
//        splash.verifyNavigation();
//
////        coachWelcome.verifyNavigation(120);
////        coachWelcome.tapWelcomeButton();
////
////        if (privacy == true) {
////            privacyPage.verifyNavigation();
////            privacyPage.tapPolicyConfirmButton();
////        }
////
////        addPage.verifyNavigation();
//    }
//
//	public void navigateToPrivacyPolicyPage(boolean privacy) {
//        splash.verifyNavigation();
//
////        coachWelcome.verifyNavigation(120);
////        coachWelcome.tapWelcomeButton();
////
////        if (privacy == true) {
////            privacyPage.verifyNavigation();
////            privacyPage.tapPolicyConfirmButton();
////        }
////
////        addPage.verifyNavigation();
//    }
//
//
//}
