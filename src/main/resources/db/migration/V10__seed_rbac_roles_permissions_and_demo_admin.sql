INSERT INTO roles (name, code, description, created_at, modified_at)
VALUES
    ('Administrator', 'ADMIN', 'Full platform administrator with all permissions.', NOW(), NOW()),
    ('Customer', 'CUSTOMER', 'Default shopper role for registered customers.', NOW(), NOW()),
    ('Product Manager', 'PRODUCT_MANAGER', 'Manages product and category catalog operations.', NOW(), NOW()),
    ('Inventory Manager', 'INVENTORY_MANAGER', 'Manages product inventory and stock movements.', NOW(), NOW()),
    ('Support Agent', 'SUPPORT_AGENT', 'Supports customer order lookup and cancellation workflows.', NOW(), NOW())
ON CONFLICT (code) DO NOTHING;

INSERT INTO permissions (name, code, description, created_at, modified_at)
VALUES
    ('Read Products', 'PRODUCT_READ', 'Allows reading product catalog data.', NOW(), NOW()),
    ('Create Products', 'PRODUCT_CREATE', 'Allows creating product catalog records.', NOW(), NOW()),
    ('Update Products', 'PRODUCT_UPDATE', 'Allows updating product catalog records.', NOW(), NOW()),
    ('Delete Products', 'PRODUCT_DELETE', 'Allows deleting product catalog records.', NOW(), NOW()),
    ('Read Categories', 'CATEGORY_READ', 'Allows reading product category data.', NOW(), NOW()),
    ('Create Categories', 'CATEGORY_CREATE', 'Allows creating product category records.', NOW(), NOW()),
    ('Update Categories', 'CATEGORY_UPDATE', 'Allows updating product category records.', NOW(), NOW()),
    ('Delete Categories', 'CATEGORY_DELETE', 'Allows deleting product category records.', NOW(), NOW()),
    ('Read Inventory', 'INVENTORY_READ', 'Allows reading inventory records.', NOW(), NOW()),
    ('Manage Inventory', 'INVENTORY_MANAGE', 'Allows inventory creation, adjustment, reservation, and release.', NOW(), NOW()),
    ('Read Orders', 'ORDER_READ', 'Allows reading customer order records.', NOW(), NOW()),
    ('Update Orders', 'ORDER_UPDATE', 'Allows updating customer order records.', NOW(), NOW()),
    ('Cancel Orders', 'ORDER_CANCEL', 'Allows cancelling eligible customer orders.', NOW(), NOW()),
    ('Read Users', 'USER_READ', 'Allows reading user account records.', NOW(), NOW()),
    ('Manage Users', 'USER_MANAGE', 'Allows managing user account records.', NOW(), NOW()),
    ('Read Roles', 'ROLE_READ', 'Allows reading RBAC roles.', NOW(), NOW()),
    ('Create Roles', 'ROLE_CREATE', 'Allows creating RBAC roles.', NOW(), NOW()),
    ('Update Roles', 'ROLE_UPDATE', 'Allows updating RBAC roles.', NOW(), NOW()),
    ('Delete Roles', 'ROLE_DELETE', 'Allows deleting RBAC roles.', NOW(), NOW()),
    ('Read Permissions', 'PERMISSION_READ', 'Allows reading RBAC permissions.', NOW(), NOW()),
    ('Create Permissions', 'PERMISSION_CREATE', 'Allows creating RBAC permissions.', NOW(), NOW()),
    ('Update Permissions', 'PERMISSION_UPDATE', 'Allows updating RBAC permissions.', NOW(), NOW()),
    ('Delete Permissions', 'PERMISSION_DELETE', 'Allows deleting RBAC permissions.', NOW(), NOW()),
    ('Assign Roles', 'ROLE_ASSIGN', 'Allows assigning roles to users.', NOW(), NOW()),
    ('Assign Permissions', 'PERMISSION_ASSIGN', 'Allows assigning permissions to roles.', NOW(), NOW())
ON CONFLICT (code) DO NOTHING;

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
CROSS JOIN permissions p
WHERE r.code = 'ADMIN'
ON CONFLICT DO NOTHING;

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
JOIN permissions p ON p.code IN (
    'PRODUCT_READ',
    'PRODUCT_CREATE',
    'PRODUCT_UPDATE',
    'PRODUCT_DELETE',
    'CATEGORY_READ',
    'CATEGORY_CREATE',
    'CATEGORY_UPDATE',
    'CATEGORY_DELETE'
)
WHERE r.code = 'PRODUCT_MANAGER'
ON CONFLICT DO NOTHING;

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
JOIN permissions p ON p.code IN ('INVENTORY_READ', 'INVENTORY_MANAGE')
WHERE r.code = 'INVENTORY_MANAGER'
ON CONFLICT DO NOTHING;

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM roles r
JOIN permissions p ON p.code IN ('ORDER_READ', 'ORDER_CANCEL')
WHERE r.code = 'SUPPORT_AGENT'
ON CONFLICT DO NOTHING;

INSERT INTO users (
    first_name,
    last_name,
    username,
    email,
    password,
    phone_number,
    is_active,
    created_at,
    modified_at,
    created_by,
    modified_by
)
VALUES (
    'Demo',
    'Admin',
    'admin',
    'admin@example.com',
    '$2a$10$KVRXQyQb86Aza2PV.4K9mOPxo4waXjUk5mGTsy6QtvVDdCzy6ykk6', --- password: StrongPass123
    NULL,
    TRUE,
    NOW(),
    NOW(),
    NULL,
    NULL
)
ON CONFLICT DO NOTHING;

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
JOIN roles r ON r.code = 'ADMIN'
WHERE u.username = 'admin'
ON CONFLICT DO NOTHING;

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
JOIN roles r ON r.code = 'CUSTOMER'
WHERE u.username <> 'admin'
ON CONFLICT DO NOTHING;
