pragma solidity ^0.4.11;

contract Smartraise {

    address owner;

    function Smartraise(){
        owner = msg.sender;
    }

    function kill() {
        if (msg.sender == owner) {
            selfdestruct(owner);
        }
    }

}
