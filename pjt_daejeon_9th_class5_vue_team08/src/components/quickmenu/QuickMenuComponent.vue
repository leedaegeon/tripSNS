<template>
  <div class="quickmenu" style="position: sticky; top: 80px">
    <div class="quickmenu">
      <div class="card-body">
        
        <b-row class="mt-3">
          <b-col align="center">
            <b-button @click="movePage" id="mk-pln-btn" class="m-1"
              >플랜 만들기</b-button
            >
          </b-col>
        </b-row>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "QuickMenuComponent",
  methods: {
    movePage() {
      this.$router.push({ name: "pwrite" });
    },
  },
};
</script>

<style scoped>
.quickmenu{
  margin-top: 20rem;
  margin-right: 10rem;
  
}
#mk-pln-btn{
  border-radius: 100px;
  width: 7rem;
  height: 7rem;
  font-weight: bold;
  font-size: 12pt;
  color:#1e56a0c1;
  background-color: #ffffff;
  border: 2px solid #1e56a0c1;
  padding: none;
}
#mk-pln-btn:hover{
  
  color:#f5f5f5;
  background-color: #1e56a0c1;
  border: none;

}
</style>
