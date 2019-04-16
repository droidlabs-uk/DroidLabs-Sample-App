# Blockchain Transactions Challenge

### Challenge
Display a scrollable list of transactions retrieved from our `multiaddress API`. Each item should at least have a BTC value, some indication if money was sent or received and a transaction time. Please deliver the code as a zipped project so that it can be built locally, or host the project in a public GitHub/Bitbucket repo.
### Details
Retrieve the data via GET https://blockchain.info/multiaddr?active=xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ. You are welcome to use your own xPub or address if you so wish.

##### Demonstrated:
- modularised application
- Dagger2 dependency injection
- Retrofit 
- RxAndroid / RxJava
- MVI - architecture 
- Coroutines
- JUnit4 Testing
- proper Gradle dependency setup
- simple UI via RecyclerView

##### Known limitations:
- single `address` as input parameter to `multiAddress` API-endpoint
- testing of MVI presenter missing
- no `refresh()` without app close
- `TransactionsFragmentCoroutine` not used / displayed -> could be TabLayout
- no persistence layer / caching

##### Nice to haves:
- navigation interface between fragments
- BottomNavigationBar / Tabbed Layout
- Animation filling the list top to bottom
- detailed view of each transaction
- better abstracted network layer
- Room DB