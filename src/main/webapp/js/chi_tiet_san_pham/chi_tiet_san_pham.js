// $(document).ready(function () {
//   $('#searchName0').select2({
//         width: 413,
//         placeholder: "Chon Co Ao ....",
//         ajax: {
//             type: 'GET',
//             url: '/chi-tiet-san-pham/search2-co-ao',
//             data: function (params) {
//                 return {
//                     keyword: params.term || '',
//                 };
//             },
//             processResults: function (data) {
//                 return {
//                     results: $.map(data, function (item) {
//                         return {
//                             text: item.ten,
//                             id: item.id
//                         }
//                     })
//                 };
//             }
//         }
//     });
// });


 $(document).ready(function () {
    $('#searchName1').select2({
        width: 413,
        placeholder: "Chọn kích cỡ ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search2-kich-co',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.ten,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});
$(document).ready(function () {
    $('#searchName2').select2({
        width: 413,
        placeholder: "Chon Mau Sac ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search2-mau-sac',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.ten,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});
$(document).ready(function () {
    $('#searchName3').select2({
        width: 413,
        placeholder: "Chon Thuong Hieu ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search2-thuong-hieu',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.ten,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});


$(document).ready(function () {
    $('#searchName4').select2({
        width: 413,
        placeholder: "Chon Chat Lieu ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search2-chat-lieu',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.ten,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});
$(document).ready(function () {

  $('#searchName10').select2({
        width: 413,
        placeholder: "Chon Co Ao ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search22-loai-giay',
            data: function (params) {
                return {
                    keyword: params.term || '',
                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.ten,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});

$(document).ready(function () {
    $('#searchName11').select2({
        width: 413,
        placeholder: "Chon Kich Co ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search22-kich-co',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.ten,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});
$(document).ready(function () {
    $('#searchName12').select2({
        width: 413,
        placeholder: "Chon Mau Sac ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search22-mau-sac',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.ten,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});
$(document).ready(function () {
    $('#searchName13').select2({
        width: 413,
        placeholder: "Chon Thuong Hieu ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search22-de-giay',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.ten,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});


$(document).ready(function () {
    $('#searchName14').select2({
        width: 413,
        placeholder: "Chon Chat Lieu ....",
        ajax: {
            type: 'GET',
            url: '/chi-tiet-san-pham/search22-chat-lieu',
            data: function (params) {
                return {
                    keyword: params.term || '',

                };
            },
            processResults: function (data) {
                return {
                    results: $.map(data, function (item) {
                        return {
                            text: item.ten,
                            id: item.id
                        }
                    })
                };
            }
        }
    });
});

