
<template>
    <html>
    <head>
    <title>Monster Slayer</title>
    <link rel="stylesheet" href="css/foundation.min.css">
    <link rel="stylesheet" href="css/app.css">
</head>
<body>
<div id="app" v-show="flag">
    <section class="row">
        <div class="small-6 columns">
            <h1 class="text-center">YOU</h1>
            <div class="healthbar">
                <div class="healthbar text-center" :style="{width : youHealth + '%'}"
                style="background-color: green; margin: 0; color: white;">
                    {{ youHealth }}%
                </div>
            </div>
        </div>
        <div class="small-6 columns">
            <h1 class="text-center">MONSTER</h1>
            <div class="healthbar">
                <div class="healthbar text-center" :style="{width : monsterHealth + '%'}" :monsterHealth="monsterHealth" style="background-color: green; margin: 0; color: white;">
                    {{ monsterHealth }}%
                </div>
            </div>
        </div>
    </section>
    <section class="row controls">
        <div class="small-12 columns">
            <button id="start-game" @click="restartGame">START NEW GAME</button>
        </div>
    </section>
    <section class="row controls" v-show="showControls">
        <div class="small-12 columns">
            <button id="attack" @click="attack" >ATTACK</button>
            <button id="special-attack" @click="specialAttack">SPECIAL ATTACK</button>
            <button id="heal" @click="heal" v-if="this.youHealth != 100">HEAL</button>
            <button id="give-up" @click="giveUp">GIVE UP</button>
            <button id="dont-press" @click="dontPress">MYSTERY CLICK</button>
            
        </div>
    </section>
    <section class="row log">
        <div class="small-12 columns">
            <ul >
                <li :class="{'monster-turn' : log.turn == 'm', 'player-turn' : log.turn == 'p'}" v-for="log in datalog" :key="log">
                    {{ log.text }}
                </li>
            </ul>
        </div>
    </section>
</div>
</body>
</html>
</template>
<script>
    export default{
        name: 'Game-game',
        data(){
            return{
                youHealth: 100,
                monsterHealth: 100,
                monsterDamage: 0,
                youDamage: 0,
                flag: true,
                showControls: true,
                datalog: [],
                dontClick: true,
                choice: 0
            }
        },
        methods:{
            monsterAttack(){
                this.monsterDamage = 5 + Math.floor(Math.random() * (12 - 3) + 1);
                this.youHealth -= this.monsterDamage;
                this.logData({turn : "m", text : " monster hits player for " + this.monsterDamage})
                if(this.youHealth <= 0 || this.monsterHealth <= 0){
                    if(this.youHealth >= 0){
                        
                        alert("You win, restart the game?");
                        this.restartGame();
                    }
                    else{
                        alert("Monster wins, restart the game?");
                        this.restartGame();
                    }
                }
            },
            attack(){
                this.youDamage = 3 + Math.floor(Math.random() * (10 - 3) + 1);
                this.monsterHealth -= this.youDamage;
                
                this.logData({turn : "p", text : " player hit monster for " + this.youDamage })
                this.monsterAttack();
            },
            specialAttack(){
                
                this.youDamage = 10 + Math.floor(Math.random() * 11);
                this.monsterHealth -= this.youDamage;
                
                this.logData({turn : "p", text : " player hit monster hard for " + this.youDamage});
                this.monsterAttack();
            },
            heal(){
                if(this.youHealth <= 90){
                    this.youHealth += 10;
                }
                else if(this.youHealth == 91){
                    this.youHealth += 9;
                }
                else if(this.youHealth == 92){
                    this.youHealth += 8;
                }
                else if(this.youHealth == 93){
                    this.youHealth += 7;
                }
                else if(this.youHealth == 94){
                    this.youHealth += 6;
                }
                else if(this.youHealth == 95){
                    this.youHealth += 5;
                }
                else if(this.youHealth == 96){
                    this.youHealth += 4;
                }
                else if(this.youHealth == 97){
                    this.youHealth += 3;
                }
                else if(this.youHealth == 98){
                    this.youHealth += 2;
                }
                else if(this.youHealth == 99){
                    this.youHealth += 1;
                }
                else{
                    this.youHealth += 0;
                }

                this.logData({turn : "p", text : " player healed for 10"});
            },
            giveUp(){
                this.showControls = false;
                this.logData({turn : "p", text : " player gave up"});
            },
            restartGame(){
                this.youHealth = 100;
                this.monsterHealth = 100;
                this.monsterDamage = 0;
                this.youDamage = 0;
                this.flag = true;
                this.showControls = true;
                this.datalog = [];
            },
            logData(log){
                this.datalog.unshift(log);
            },
            dontPress(){
                this.dontClick = false;
                this.choice = Math.floor(Math.random() * 2);
                if(this.choice == 0){
                    this.youHealth = 1;
                    this.logData({turn : "p", text : " player is almost dead"});
                }
                else{
                   this.monsterHealth = 100; 
                   this.logData({turn : "m", text : " monster got healed completely"});
                }
                
            }
        }
    }
</script>





<style scoped>

@import url('../css/foundation.min.css');
@import url('../css/app.css');
</style>