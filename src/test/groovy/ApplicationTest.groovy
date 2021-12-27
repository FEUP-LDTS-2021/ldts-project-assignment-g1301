import spock.lang.Specification

class ApplicationTest extends Specification {
    def "Test 1"(){
        when:
            int x=2
        then:
            2==x
    }
}
