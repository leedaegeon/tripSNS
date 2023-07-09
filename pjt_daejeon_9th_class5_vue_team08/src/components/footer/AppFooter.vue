<template>
  <div>
    <go-top class="to-top" bg-color="#1e56a0c1" :size="size"></go-top>
    <div class="dumm"></div>
    <div class="btm">
      <p>Copyright © 2023 bebetour. All rights reserved.</p>
      <address>Contact webmaster for more information. 010-1234-5678</address>
    </div>
  </div>
</template>

<script>
import GoTop from "@inotom/vue-go-top";
export default {
  name: "AppFooter",
  components: { GoTop },
  data() {
    return {
      size: 50,
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.dumm {
  height: 60rem;
}
.btm {
  background: #1e56a0c1;
  height: 160px;
  width: 100%;
  position: absolute;
  text-align: center;
  color: white;
  padding-top: 50px;
}

</style>
