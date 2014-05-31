watch('src/(main)?(test)?/java/*') do |file|
  system "gradle --daemon test"
end
