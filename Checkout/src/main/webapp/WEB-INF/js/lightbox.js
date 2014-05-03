$(function() {
	$(document).on('click', "#fade", function() {
		killLightBoxWithFire();
	});
});

function summonLightBox(url) {
	var content = '<p>Você tem certeza que deseja excluir?</p><br/><a class="selectAddress" id="optionInLightBox" href="'+url+'" >Sim</a>&nbsp;&nbsp;<a id="optionInLightBox" href="javascript:void(0)" onclick = "killLightBoxWithFire()" class="selectAddress" >Não</a>';
	$('#light').removeClass("hidden").html(content);
	$('#fade').removeClass("hidden");
}

function killLightBoxWithFire() {
	$('#light').addClass("hidden");
	$('#fade').addClass("hidden");
}