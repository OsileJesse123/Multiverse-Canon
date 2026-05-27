run_development:
	flutter run --flavor development

run_production:
	flutter run --flavor production

run_staging:
	flutter run --flavor staging

clean:
	flutter clean

get:
	flutter pub get

open_ios:
	open ios/Runner.xcworkspace