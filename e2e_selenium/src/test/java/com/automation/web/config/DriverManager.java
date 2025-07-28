package com.automation.web.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class DriverManager {

	private static final Logger logger = LogManager.getLogger(DriverManager.class);
	private static final String CHROME = "chrome";
	private static final String FIREFOX = "firefox";
	private static final String EDGE = "edge";
	private static final String BROWSER_PROPERTY = "browser";
	private static final String HEADLESS_PROPERTY = "headless";
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static ThreadLocal<Path> userDataDir = new ThreadLocal<>();

	public static WebDriver getDriver() {
		if (driver.get() == null) {
			logger.info("Initializing new WebDriver for current thread.");
			String browser = System.getProperty(BROWSER_PROPERTY, CHROME);
			boolean headless = Boolean.parseBoolean(System.getProperty(HEADLESS_PROPERTY, "true"));
			setDriver(browser, headless);
		}
		return driver.get();
	}

	private static void setDriver(String browser, boolean headless) {
		try {
			switch (browser.toLowerCase()) {
			case CHROME:
				ChromeOptions chromeOptions = setupChromeOptions(headless);
				driver.set(new ChromeDriver(chromeOptions));
				break;
			case FIREFOX:
				FirefoxOptions firefoxOptions = setupFirefoxOptions(headless);
				driver.set(new FirefoxDriver(firefoxOptions));
				break;
			case EDGE:
				EdgeOptions edgeOptions = setupEdgeOptions(headless);
				driver.set(new EdgeDriver(edgeOptions));
				break;
			default:
				throw new IllegalArgumentException("Browser not supported: " + browser);
			}
		} catch (Exception e) {
			logger.error("Failed to initialize WebDriver for browser '{}': {}", browser, e.getMessage(), e);
			throw new RuntimeException("WebDriver initialization failed for browser: " + browser, e);
		}

		WebDriver currentDriver = driver.get();
		if (currentDriver != null) {
			currentDriver.manage().window().maximize();
			currentDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			currentDriver.manage().deleteAllCookies();
			logger.info("WebDriver initialized successfully for browser: {}. Headless: {}", browser, headless);
		} else {
			logger.error("WebDriver instance is null after initialization attempt. Cannot apply common settings.");
		}
	}

	private static ChromeOptions setupChromeOptions(boolean headless) throws java.io.IOException {
		ChromeOptions chromeOptions = new ChromeOptions();
		if (headless) {
			chromeOptions.addArguments("--headless=new");
			chromeOptions.addArguments("--window-size=1920,1080");
		}
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("--force-device-scale-factor=1");

		Path tempUserDataDir = Files.createTempDirectory("chrome-user-data-");
		userDataDir.set(tempUserDataDir);
		chromeOptions.addArguments("--user-data-dir=" + tempUserDataDir.toAbsolutePath());

		chromeOptions.addArguments("--disable-application-cache");
		chromeOptions.addArguments("--disk-cache-size=0");
		chromeOptions.addArguments("--disable-features=NetworkService,NetworkServiceInProcess");
		chromeOptions.addArguments("--incognito");

		return chromeOptions;
	}

	private static FirefoxOptions setupFirefoxOptions(boolean headless) {
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		if (headless) {
			firefoxOptions.addArguments("--headless");
			firefoxOptions.addArguments("--disable-gpu");
			firefoxOptions.addArguments("--no-sandbox");
		}
		firefoxOptions.addPreference("browser.cache.disk.enable", false);
		firefoxOptions.addPreference("browser.cache.memory.enable", false);
		firefoxOptions.addPreference("browser.cache.offline.enable", false);
		firefoxOptions.addPreference("network.http.use-cache", false);
		firefoxOptions.addPreference("browser.privateBrowse.autostart", true);

		return firefoxOptions;
	}

	private static EdgeOptions setupEdgeOptions(boolean headless) {
		EdgeOptions edgeOptions = new EdgeOptions();
		if (headless) {
			edgeOptions.addArguments("--headless=new");
			edgeOptions.addArguments("--disable-gpu");
			edgeOptions.addArguments("--no-sandbox");
			edgeOptions.addArguments("--disable-dev-shm-usage");
			edgeOptions.addArguments("--window-size=1920,1080");
		}
		return edgeOptions;
	}

	public static void quitDriver() {
		logger.info("Quitting WebDriver instance.");
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}

		if (userDataDir.get() != null) {
			try {
				Files.walk(userDataDir.get()).sorted(java.util.Comparator.reverseOrder()).map(Path::toFile)
						.forEach(File::delete);
				logger.info("Successfully deleted temporary user data directory: {}", userDataDir.get());
			} catch (Exception e) {
				logger.warn("Could not delete temporary user data directory: {} - {}", userDataDir.get(),
						e.getMessage());
			} finally {
				userDataDir.remove();
			}
		}
	}
}