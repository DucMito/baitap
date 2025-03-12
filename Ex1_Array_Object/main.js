// Danh sách các sản phẩm có trong giỏ hàng
let products = [
    {
        name: "Iphone 13 Pro Max",
        price: 3000000,
        brand: "Apple",
        count: 2,
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
];

// 1. In ra thông tin các sản phẩm trong giỏ hàng theo cấu trúc yêu cầu
console.log("Thông tin các sản phẩm trong giỏ hàng:");
products.forEach(product => {
    console.log(`${product.name} - ${product.price} - ${product.brand} - ${product.count}`);
});

// 2. Tính tổng tiền tất cả sản phẩm trong giỏ hàng
let totalPrice = products.reduce((total, product) => total + product.price * product.count, 0);
console.log(`Tổng tiền tất cả sản phẩm trong giỏ hàng: ${totalPrice}`);

// 3. Tìm các sản phẩm của thương hiệu "Apple"
let appleProducts = products.filter(product => product.brand === "Apple");
console.log("Sản phẩm của Apple:");
appleProducts.forEach(product => {
    console.log(`${product.name} - ${product.price} - ${product.brand} - ${product.count}`);
});

// 4. Tìm các sản phẩm có giá > 20000000
let expensiveProducts = products.filter(product => product.price > 20000000);
console.log("Sản phẩm có giá > 20000000:");
expensiveProducts.forEach(product => {
    console.log(`${product.name} - ${product.price} - ${product.brand} - ${product.count}`);
});

// 5. Tìm các sản phẩm có chữ "pro" trong tên (Không phân biệt hoa thường)
let proProducts = products.filter(product => product.name.toLowerCase().includes("pro"));
console.log("Sản phẩm có chữ 'pro' trong tên:");
proProducts.forEach(product => {
    console.log(`${product.name} - ${product.price} - ${product.brand} - ${product.count}`);
});

// 6. Thêm 1 sản phẩm bất kỳ vào trong mảng product
products.push({
    name: "Xiaomi Mi 11 Ultra",
    price: 24000000,
    brand: "Xiaomi",
    count: 1,
});
console.log("Danh sách sản phẩm sau khi thêm mới:");
products.forEach(product => {
    console.log(`${product.name} - ${product.price} - ${product.brand} - ${product.count}`);
});

// 7. Xóa tất cả sản phẩm của thương hiệu "Samsung" trong giỏ hàng
products = products.filter(product => product.brand !== "Samsung");
console.log("Danh sách sản phẩm sau khi xóa sản phẩm của Samsung:");
products.forEach(product => {
    console.log(`${product.name} - ${product.price} - ${product.brand} - ${product.count}`);
});

// 8. Sắp xếp giỏ hàng theo price tăng dần
products.sort((a, b) => a.price - b.price);
console.log("Danh sách sản phẩm sau khi sắp xếp theo price tăng dần:");
products.forEach(product => {
    console.log(`${product.name} - ${product.price} - ${product.brand} - ${product.count}`);
});

// 9. Sắp xếp giỏ hàng theo count giảm dần
products.sort((a, b) => b.count - a.count);
console.log("Danh sách sản phẩm sau khi sắp xếp theo count giảm dần:");
products.forEach(product => {
    console.log(`${product.name} - ${product.price} - ${product.brand} - ${product.count}`);
});

// 10. Lấy ra 2 sản phẩm bất kỳ trong giỏ hàng
let randomProducts = products.slice(0, 2); // Lấy 2 sản phẩm đầu tiên
console.log("2 sản phẩm bất kỳ trong giỏ hàng:");
randomProducts.forEach(product => {
    console.log(`${product.name} - ${product.price} - ${product.brand} - ${product.count}`);
});
